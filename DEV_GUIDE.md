# Stellarity Enlightened Dev Guide

## Basic Project Tooling Info

This project uses deobfuscated Mojmap, and may be inconsistent with old Fabric tutorials using yarn. Should you ever
need to apply an old fabric tutorial to implement something,
use [Linkie](https://linkie.shedaniel.dev/mappings?namespace=yarn&translateMode=ns&translateAs=mojang_raw&search=) for
Mojmap and Yarn conversions (or general lookups).

This project also uses [Stonecutter](https://stonecutter.kikugie.dev/). For the most part you will likely NOT need to
modify any of this, but if you do you should reach out. `build.gradle.kts` resides the file for configuration datagen,
run tasks, publishing, dependencies, etc. `settings.gradle.kts` carries info related to Stonecutter. We mostly will not
use this file unless upgrading versions. `stonecutter.gradle.kts` SHOULD NOT BE touched, unless cleared.

## General Project Structure

Use `prismatic.shards.stellarity.snake_case_for_packages.PascalCaseForClasses`

If you know a class will only contain `static final` fields and `static` methods, we use an `interface` to implement the
class.

If you know that a class will mostly be an initialize one, never write class, we will implement it as a `record`.

Use explicit types, implicit `var` idgaf tbh cuz at the end of the day we all have IDEs. And plus, multiversion works
better with vars (if we get versions worth multiversioning)

## Registries

Registries are in the format `Stellarity[TypeName]s` such as `StellarityBlocks` for **Block** registry in the `registry`
package.

For custom Stellarity definitions for registries, use `block.CustomBlock` for example. Note the package is singular.

## Resource Keys

We track resource keys for data driven definitions in `key` package. Name formatting is mostly the same, except they
should be in this format instead

```java
public interface StellarityExampleTypes {
  ResourceKey<Type> AN_EXAMPLE = id("an_example");

  static ResourceKey<Type> id(String id) {
    return Stellarity.key(Registries.TYPE, id);
  }
}
```

## Tag Keys

This is the exact same as Resource Keys except it is now `TagKey.create(Registries.TYPE, Stellarity.id(id))`

## Mixins

All mixins are defined in the format `mixin.purpose_or_targetted_noun.[VanillaClassName]Mixin`

This is to keep code clean and easy to find relevant mixins corresponding to a purpose. DO NOT add these to
`fabric.mod.json` despite the warning. Stonecutter's Fletching Table tool handles registration.

In the event you get an `Not transformed` exception, run the clean task. Then check to see if your code has gone wrong
if it persists.

## Interface Injections

These are used for extending vanilla classes with extra definitions. They must be in `Ext[VanillaClass]` and
`Ext[VanillaClass][InnerClass]` if inner classes are ambigious.

Methods MUST be `default`, `throw AssertionError("Not transformed!")`, and be prefixed with `stellarity$`

They should then be implemented in mixins in `mixin.interface_injections`. Injection mixins should NOT contain anything
else and must be defined there and there only.

## Datagen

All providers go in the root of `datagen` except if:

- If you are generating tags, they must go in `datagen.tags`
- If you are generating modnomicon book related assets, they must go in `datagen.book`
- If you are generating loot table related assets, they must go in `datagen.loot_table`
- If you are generating any other data-driven registry, they can go inside `datagen.dynamic` and should have relevant
  methods added to `DynamicRegistryProvider.buildRegistry` and `DynamicRegistryProvider.configure`

## Client

**IMPORTANT: Always annotate client package with
`@Environment(EnvType.CLIENT)`**

**This prevents server side crashes and all client code MUST be annotated and be inside this package**

All client generally follow the same conventions mentioned above, but the "root" now is
`prismatic.shards.stellarity.client`

Are you unsure about if it should be client or not? Check the related vanilla class you are trying to build for, and if
those are annotated, move them to client.

## Util

Utilities can go in `util`.

## Adding Translations

Make sure the translation submodule is added to your project. If it is not, add it by running
`git submodule update --init --recursive` in the root of the project.

Translations should be added via the translation python script. This helps manage automatic synchronization between the
resources folder and the git submodule.

## Commit Guides

Don't write an essay, and try to use these emojis at the beginning of commit message whenever possible.

- ✨ - New feature
- 🐛 - Bug fix
- 🧹 - Code refactor/cleanup
- 📝 - Documentation
- 🚑 - Hotfix

## Further Questions

If you have further questions, feel free to ask Amber or any experienced dev on the team. 




