import pathlib

from pydub import AudioSegment

pathlib.Path("input_audio").mkdir(exist_ok=True)
pathlib.Path("output_audio/altar_of_the_accursed").mkdir(exist_ok=True, parents=True)

# everything below here is good for experimentation
# this python file is for writing quick scripts to merge files

tridents = ["trident/thunder1.ogg", "trident/thunder2.ogg"]
wardens = ["warden/heartbeat_1.ogg", "warden/heartbeat_2.ogg", "warden/heartbeat_3.ogg", "warden/heartbeat_4.ogg"]

index = 1

for trident in tridents:
	for warden in wardens:
		print(trident, warden)
		trident_sound = AudioSegment.from_ogg(f"input_audio/{trident}")
		warden_sound = AudioSegment.from_ogg(f"input_audio/{warden}")
		if len(trident_sound) > len(warden_sound):
			new_sound = trident_sound.overlay(warden_sound)[:3000]
			new_sound.export(f"output_audio/altar_of_the_accursed/craft_{index}.ogg", format="ogg")
		index += 1
