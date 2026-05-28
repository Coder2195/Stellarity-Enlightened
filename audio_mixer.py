import os
import pathlib
from time import sleep

from pydub import AudioSegment
from pydub.playback import play

pathlib.Path("input_audio").mkdir(exist_ok=True)
pathlib.Path("output_audio").mkdir(exist_ok=True)

# everything below here is good for experimentation
# this python file is for writing quick scripts to merge files

tridents = ["trident/thunder1.ogg", "trident/thunder2.ogg"]
wardens = ["warden/heartbeat_1.ogg", "warden/heartbeat_2.ogg", "warden/heartbeat_3.ogg", "warden/heartbeat_4.ogg"]

index = 1

for trident in tridents:
	for wardens in wardens:
		trident_sound = AudioSegment.from_ogg(f"input_audio/{trident}")
		warden_sound = AudioSegment.from_ogg(f"input_audio/{wardens}")
		if (len(trident_sound) > len(warden_sound)):
			new_sound = trident_sound.overlay(warden_sound)
			play(new_sound)
		index += 1
