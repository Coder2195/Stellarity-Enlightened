# 0.7.0

# Changes

Unless annotated, all changes are considered to be up to date with
the [wiki](https://koharasbasement.wiki.gg/wiki/Stellarity) and the [datapack](https://modrinth.com/datapack/stellarity)

## Items

**[+] Flesh Piglin Spawn Egg**

**[*] Call of the Void**

- Fully implemented

**[*] Satchel of Voids**

- Fully implemented and acts as a temporary altar of the accursed, summoning a sigil when used

**[+] Sharanga**

**[+] Spectral Fury**

**[+] Copper Elektra Shield**

**[+] Enderman's Hand**

**[+] Dragon's Eye**

**[+] Phantom Wings**

**[+] Life Crystal**

- Does nothing at the moment

## Blocks

**[*] Coarse Ender Dirt**

- Applied correct textures to the block and translations

**[*] Altar of the Accursed**

- Added proper crafting noise for the altar

**[+] Colored Leaves**

- Experimental, may be removed. Used as proof of concept for future trees.

## Entities

**[+] Voided Arrow**

- Shot from the Call of the Void and has no corresponding item, because it shatters

**[+] Satchel Sigil**

Unless annotated, all changes are considered to be up to date with
the [wiki](https://koharasbasement.wiki.gg/wiki/Stellarity) and the [datapack](https://modrinth.com/datapack/stellarity)

**[+] Spectral Bolt**

- Shot from the Sharanga and has no corresponding item, because it shatters

**[+] Spectral Wisp**

- Shot from the Spectral Fury and has no corresponding item, because it disappears

**[*] Voided Skeleton**

- Fixes bug where it takes damage from harming effect
- Wields the Call of the Void instead of a bow
- Now can burn in daylight

**[*] Flesh Piglin**

- Fixes bug where it takes damage from harming effect

**[*] Voided Zombie**

- Fixes bug where it takes damage from harming effect
- Now can burn in daylight

**[+] Zombie Villager**

- Corrected Stellarity professions from spawning in non end biomes
- Added proper textures for Stellarity professions

**[*] Shulker**

- Modified loot table to Stellarity's loot table

**[*] Phantom**

- Modified loot table to Stellarity's loot table
- New entity sub predicate `stellarity:nbt_number` is available for use in datapacks
- New number provider `stellarity:nbt_number` is available for use in datapacks
- New number provider `stellarity:multiply` is available for use in datapacks

**[*] Pixie**

- Proper Item Model

## Advancements

**[*] Topped Off**

- Uses item attributes now

**[+] Electrified**

- New trigger `stellarity:dash` is available for use in datapacks

**[+] Night Sky Stalkers**

**[+] Worsening Nightmares**

**[+] Timeless Traveler**

**[*] Topped Off**

- New entity sub predicate `stellarity:entity_attribute_modifiers` is available for use in datapacks

## Recipes

### AOTA

**[+] Sharanga + 3 Diamond + 8 Phantom Membrane + Enderite Upgrade = Spectral Fury**

**[+] Netherite Sword + Enderite Upgrade + 8 Enderite Shard + Wither Skull = Tamaris**

## Biomes

**[*] Ashfall Deltas**

- Added voided slime to spawn settings
- Removed tropical fish from spawn settings because of its vanilla spawn requirements

**[*] Frozen Spikes**

- Fixes the `spike_feature` from not generating due to an computation error

**[*] Prismarine Forest**

- Added frog to spawn settings

**[*] Warped Marsh**

- Added voided slime to spawn settings
- Added frog to spawn settings
- Removed tropical fish from spawn settings

## Mob Effects

**[*] Voided**

- Add sound effect for when the effect is applied and removed

## Versions

**[*] 26.1.x -> 26.2**

## Structures

**[+] Campsite**

## Mechanics

**[*] Animal Spawning**

- Fixed bug where animals would not spawn on chunk generation in the end
- Fixed bug where frogs and wolves would not spawn at all in the end

**[*] Item Tooltips**

- Sychronizes most of the item tooltips and item name colors from the datapack. Report any missing or incorrect tooltips

**[*] Configuration**

- Use `/stellarity_config` to open the configuration menu
- Added a configuration menu and proper packets
- Permissions are now handled correctly

**[*] Totem Saving**

- Obeys configuration

# Developer's Note

A large update, but the next one will be even larger with villager and end villages. Stay tuned!
