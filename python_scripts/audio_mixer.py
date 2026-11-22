import json
import os
import pathlib
from functools import reduce
from itertools import product
from random import randrange

from pydub import AudioSegment
from pydub.utils import ratio_to_db

pathlib.Path("input_audio").mkdir(exist_ok=True)
pathlib.Path("output_audio/floral_bloom").mkdir(exist_ok=True, parents=True)

"""
Resources
https://github.com/jiaaro/pydub/issues/496 for Minecraft volume
"""


# https://batulaiko.medium.com/how-to-pitch-shift-in-python-c59b53a84b6d
# noinspection PyProtectedMember
def pitch_modulate(sound: AudioSegment, octaves: float):
	new_sample_rate = int(sound.frame_rate * (2.0 ** octaves))
	hipitch_sound = sound._spawn(sound.raw_data, overrides={'frame_rate': new_sample_rate})
	hipitch_sound = hipitch_sound.set_frame_rate(44100)
	return hipitch_sound


def mc(path: str, volume: float, pitch: float):
	return pitch_modulate(AudioSegment.from_ogg(f"../minecraft-assets/assets/minecraft/sounds/{path}.ogg"), pitch).apply_gain(ratio_to_db(volume))


def check_stereo():
	reduced_sizes = 0
	for root, dirs, files in os.walk("../src/main/resources/assets/stellarity/sounds/"):
		for name in files:
			path_joined = os.path.join(root, name)
			if not path_joined.endswith(".ogg"): continue
			audio_segment = AudioSegment.from_ogg(path_joined)
			channels = audio_segment.channels
			if channels == 1: continue

			if "stereo" in path_joined:
				continue

			prev_size = os.path.getsize(path_joined)

			audio_segment = audio_segment.set_channels(1).set_frame_rate(32000)
			audio_segment.export(path_joined, format="ogg", bitrate="64k")

			new_size = os.path.getsize(path_joined)
			print(f"{path_joined} size from {prev_size / 1000}kb to {new_size / 1000}kb ({new_size / prev_size * 100}% of original size)")
			reduced_sizes -= (prev_size - new_size)

	print(f"reduced {reduced_sizes / 1000}kb")


# check_stereo()

# everything below here is good for experimentation
# this python file is for writing quick scripts to merge files

class SoundEntry:
	sounds: list[str]
	volume: float
	pitch: float

	def __init__(self, sounds: list[str],
							 volume: float,
							 pitch: float):
		self.sounds = sounds
		self.volume = volume
		self.pitch = pitch


with open("../minecraft-assets/assets/minecraft/sounds.json") as sf:
	sounds_index = json.load(sf)

	to_combine: list[SoundEntry] = []
	chosen_sounds = []

	while True:
		chosen_sound = input("Sound: ")

		if chosen_sound == "":
			break

		if chosen_sound not in sounds_index:
			print("Sound does not exist")
			continue

		if chosen_sound in chosen_sounds:
			print("Sound chosen already")
			continue

		chosen_sounds.append(chosen_sound)
		to_combine.append(SoundEntry(sounds_index[chosen_sound]["sounds"], float(input("Volume: ") or 1), float(input("Pitch: ") or 1)))

	if len(to_combine) == 0: exit()

	# noinspection bad-assignment
	max_combo: int = reduce(lambda result, current: result * len(current.sounds), to_combine, initial=1)
	how_many = max_combo if max_combo < 2 else int(input(f"How many (1-{max_combo}): ") or max_combo)

	combinations = list(product(*map(lambda entry: entry.sounds, to_combine)))
	print(combinations)

	while len(combinations) > how_many:
		combinations.pop(randrange(0, len(combinations)))

	
	name = input("what should your files be called? ")

	for combo_idx in range(len(combinations)):
		combination = combinations[combo_idx]
		final_sound: AudioSegment | None = None

		for tuple_idx in range(len(combination)):
			this_sound = mc(combination[tuple_idx], to_combine[tuple_idx].volume, to_combine[tuple_idx].pitch)
			if final_sound is None:
				final_sound = this_sound
			else:
				diff = len(this_sound) - len(final_sound)
				if diff > 0:
					final_sound += AudioSegment.silent(duration=diff)
				final_sound = final_sound.overlay( mc(combination[tuple_idx], to_combine[tuple_idx].volume, to_combine[tuple_idx].pitch))

		if final_sound is None:
			print("warning, final sound shouldn't be None")
			continue

		final_sound.export(f"output_audio/{name}{"" if max_combo < 2 else f"_{combo_idx + 1}.ogg"}", format="ogg")




