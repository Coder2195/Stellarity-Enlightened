# 0.6.0

# Changes

Unless annotated, all changes are considered to be up to date with
the [wiki](https://koharasbasement.wiki.gg/wiki/Stellarity) and the [datapack](https://modrinth.com/datapack/stellarity)

## Items

**[*] Prismatic Pearl**

- Proper visible trail

**[*] Enderite Upgrade**

- Updated so that the upgrade name is correct

**[+] Shulker Helmet**

**[+] Shulker Chestplate**

**[+] Shulker Leggings**

**[+] Shulker Boots**

**[+] Ender Egg**

**[+] Voided Zombie Spawn Egg**

**[+] Voided Slime Spawn Egg**

**[+] Voided Skeleton Spawn Egg**

**[+] Voided Silverfish Spawn Egg**

**[+] Frost Minnow**

**[+] Goosh**

**[*] Frozen Carpaccio**

- Added the random effect feature to the item

**[+] Chorus Stew**

**[+] Potassifish**

## Blocks

**[*] Altar of the Accursed**

- Requires mineable AOTAs to now be mined with a diamond pickaxe or better

**[+] Enderite Block**

**[+] Coarse Ender Dirt**

**[*] Ender Dirt**

- Convertable to mud

**[*] Rooted Ender Dirt**

- Convertable to mud

## Advancements

**[+] Cursed Crafting**

- New criterion `special_craft` is available for use in datapacks
- This criterion is triggered for special crafting invoked by blocks such as AOTA, but will also be used for stuff like
  Cauldron and Altar of Light crafting in the future

**[+] I am Titanium!**

**[*] The End... Again...**

- Updated to use the ingame advancement detector
- Removed criterion `advancement_completed`

## Mechanics

**[*] Void Fishing**

- Updated with all correct biome loot
- Warped marsh has extra loot, with ashen and verdant froglights now.

## Recipes

### AOTA

**[+] Netherite Armor + 4 Shulker Shells + 1 Enderite Upgrade = Shulker Armor**

### Crafting Table

**[+] 9 Enderite Shards = 1 Enderite Block**

**[+] 1 Enderite Block = 9 Enderite Shards**

## Entities

**[+] End Villagers**

- End Armorer and Butcher variants added
- Missing trade for Copper Elektra Shield, will be added in a future update

**[*] Ender Dragon**

- Updated boss bar colors

**[*] End Crystal**

- Can be breakable into item
- Right clicking yields a nice noise like the original end crystal

**[*] Cat**

- Added Ender Cat variant

**[*] Cow**

- Added Ender Cow variant

**[*] Pig**

- Added Ender Pig variant

**[*] Chicken**

- Added Ender Chicken variant

**[*] Wolf**

- Added Ender Wolf variant

**[+] Voided Zombie**

**[+] Voided Slime**

**[+] Voided Skeleton**

- Has variants `stellarity:normal`, `stellarity:flesh`, `stellarity:cold` and `stellarity:ash`
- New registry `voided_skeleton_variant` for use in datapacks. Is the exact same as a frog variant besides the texture
  differences
- Added a placeholder miniboss. This one does not hold a harvester, but a level 60 enchanted netherite sword

**[+] Voided Silverfish**

**[+] Pixie**

## Mechanics

**[*] Item Tooltips**

- Uses the colors from the datapack

**[+] Configuration**

- Implemented a basic infrastructure for configuration

**[+] (Elytra) Dying**

- Implemented elytra dying in the Altar of the Accursed
- New recipe type `altar_of_the_accursed_dye` is available for use in datapacks, which is used for elytra dying on the
  Altar, but can be applied to other items, although they won't do anything.

**[+] Elytra Trails**

- Basic particles lol

## Biomes

**[*] The End**

- Updated the main island to use Stellarity's worldgen placed features

**[*] End Highlands**

- Updated to use Stellarity's worldgen placed features
- Does not use the stacked trees at the moment, quite janky and rare anyways, decided to remove them

**[*] End Midlands**

- Updated to use Stellarity's worldgen placed features
- New feature `stellarity:spike` used in replacement of an old janky spike feature. It accepts paramaters for `height`,
  `radius`, `block_state`, `can_replace`, `wind_x` and `wind_z`.

**[*] End Barrens**

- Updated to use Stellarity's worldgen placed features

**[+] Amethyst Forest**

**[+] Ashfall Deltas**

**[+] Crystal Crags**

**[+] End Shrubland**

**[+] End Wilds**

**[+] Ender Wastes**

**[+] Endless Dunes**

**[+] Fiery Hills**

**[+] Flesh Tundra**

**[+] Frosted Valley**

- Modified the `minecraft:large_dripstone` feature to accept `stellarity:block_state` for ice dripstone generation

**[+] Frozen Marsh**

- Added a new `stellarity:freeze_water` feature to freeze cold biomes

**[+] Frozen Shrubland**

**[+] Frozen Spikes**

**[+] Hallowed Tundra**

- Added a new `stellarity:hanging_column` decorator for block columns hanging from trees

**[+] Prismarine Forest**

**[+] The Hallow**

**[+] The Nest**

- Added a new `stellarity:dragon_egg` feature for an exact pixel to block generation of a giant dragon egg. Egg material
  can be configured.

**[+] Warped Marsh**

## Structures

**[*] Exit Portal**

- Implemented Stellarity exit portal chest particles

**[+] Dungeons**

- A new feature type `stellarity:dungeon` is available for use in datapacks.

**[*] Crystal Towers**

- Added a new `stellarity:has_crystal` paramater to the feature, allowing disabling of the end crystal if needed

## Versions

**[+] 26.1.1**

**[+] 26.1.2**

# Developers Note

huge update in months