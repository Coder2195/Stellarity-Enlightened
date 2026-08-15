import os
import pathlib

from pydub import AudioSegment
from pydub.playback import play
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


def mc(path: str,  volume: float, pitch: float):
	return pitch_modulate(AudioSegment.from_ogg(f"input_audio/{path}.ogg"), pitch).apply_gain(ratio_to_db(volume))



def check_stereo():
	for root, dirs, files in os.walk("src/main/resources/assets/stellarity/sounds/", topdown=False):
		for name in files:
			path_joined = os.path.join(root, name)
			if not path_joined.endswith(".ogg"): continue
			channels = AudioSegment.from_ogg(path_joined).channels
			if channels == 1: continue

			print(path_joined, channels, "NOT REMOVING BECAUSE STEREO IN NAME" if "stereo" in path_joined else "")

check_stereo()

# everything below here is good for experimentation
# this python file is for writing quick scripts to merge files
#
# first = ["entity/wind_charge/wind_burst1", "entity/wind_charge/wind_burst2", "entity/wind_charge/wind_burst3"]
# second = ["random/explode1"]
#
#
# num=0
# combo = [(a, b) for a in first for b in second]
# for (a, b) in combo:
#
# 	num+=1
# 	a_sound = mc(a, 1, 1)
# 	b_sound = mc(b, 0.333, 1.2)
#
# 	new_sound = a_sound.overlay(mc("block/beehive/enter", 1, 0.7)).overlay(a_sound).set_channels(1)
# 	play(new_sound)
# 	new_sound.export(f"output_audio/floral_bloom/bloom_{num}", format="ogg")


# thunder = ["ambient/weather/thunder1", "ambient/weather/thunder2", "ambient/weather/thunder3"]
# illusioner = ["mob/illusion_illager/mirror_move1", "mob/illusion_illager/mirror_move2"]
#
# combo = [(thun, i) for thun in thunder for i in illusioner]
# num = 0
# for (thun, i) in combo:
# 	num += 1
# 	thunder_sound = mc(thun)
# 	thunder_sound = pitch_modulate(thunder_sound, 1).apply_gain(ratio_to_db(0.8))
# 	illusioner = mc(i)
# 	bat = mc("mob/bat/takeoff")
#
# 	new_sound = thunder_sound.overlay(illusioner).overlay(bat)[:4000].set_channels(1)
# 	new_sound.export(f"output_audio/copper_elektra_shield/dash_{num}.ogg", format="ogg")

# booms = ["warden/sonic_boom1.ogg", "warden/sonic_boom2.ogg", "warden/sonic_boom3.ogg", "warden/sonic_boom4.ogg"]
# souls = ["soul_speed/soulspeed1.ogg", "soul_speed/soulspeed2.ogg", "soul_speed/soulspeed3.ogg",
#          "soul_speed/soulspeed4.ogg", "soul_speed/soulspeed5.ogg", "soul_speed/soulspeed6.ogg",
#          "soul_speed/soulspeed7.ogg", "soul_speed/soulspeed8.ogg"]
#
# combo = [(boom, soul) for boom in booms for soul in souls]
# print(combo)
# for i in range(10):
# 	boom, soul = combo.pop(randrange(len(combo)))
# 	soul_sound = AudioSegment.from_ogg(f"input_audio/{soul}")
# 	soul_sound = soul_sound.set_frame_rate(int(soul_sound.frame_rate * 0.9))
# 	boom_sound = AudioSegment.from_ogg(f"input_audio/{boom}")
# 	boom_sound = boom_sound.apply_gain(ratio_to_db(0.2))
# 	boom_sound = pitch_modulate(boom_sound, 0.8)
#
# 	new_sound = boom_sound.overlay(soul_sound)
# 	play(new_sound)
# 	new_sound.export(f"output_audio/spectral_fury/shoot_{i + 1}.ogg", format="ogg")

# altar of the accursed
# tridents = ["trident/thunder1.ogg", "trident/thunder2.ogg"]
# wardens = ["warden/heartbeat_1.ogg", "warden/heartbeat_2.ogg", "warden/heartbeat_3.ogg", "warden/heartbeat_4.ogg"]
#
# index = 1
#
# for trident in tridents:
# 	for warden in wardens:
# 		print(trident, warden)
# 		trident_sound = AudioSegment.from_ogg(f"input_audio/{trident}").apply_gain(ratio_to_db(0.88))
# 		warden_sound = AudioSegment.from_ogg(f"input_audio/{warden}")
# 		if len(trident_sound) > len(warden_sound):
# 			new_sound = trident_sound.overlay(warden_sound)[:3000]
# 			new_sound.export(f"output_audio/altar_of_the_accursed/craft_{index}.ogg", format="ogg")
# 		index += 1
