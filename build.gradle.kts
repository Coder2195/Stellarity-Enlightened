import groovy.json.JsonOutput
import groovy.json.JsonSlurper

plugins {
	id("net.fabricmc.fabric-loom") version "1.17-SNAPSHOT"
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.22"
	id("me.modmuss50.mod-publish-plugin") version "2.1.1"
	// `maven-publish`
	kotlin("jvm") version "2.2.20"
	id("com.google.devtools.ksp") version "2.2.20-2.0.4"
	id("co.uzzu.dotenv.gradle") version "4.0.0"
}

val modId = property("mod.id") as String
version = "${property("mod.version")}"
base.archivesName = modId
val requiredJava = JavaVersion.VERSION_25

repositories {
	/**
	 * Restricts dependency search of the given [groups] to the [maven URL][url],
	 * improving the setup speed.
	 */
	fun strictMaven(url: String, alias: String, vararg groups: String) = exclusiveContent {
		forRepository { maven(url) { name = alias } }
		filter { groups.forEach(::includeGroup) }
	}
	strictMaven("https://www.cursemaven.com", "CurseForge", "curse.maven")
	strictMaven("https://api.modrinth.com/maven", "Modrinth", "maven.modrinth")
	maven("https://maven.blamejared.com")
	maven("https://maven.latvian.dev/releases")
	maven("https://thedarkcolour.github.io/KotlinForForge/")
	maven("https://maven.terraformersmc.com/")

	maven("https://dl.cloudsmith.io/public/klikli-dev/mods/maven/") {
		content {
			includeGroup("com.klikli_dev")
		}
	}
}

dependencies {

	minecraft("com.mojang:minecraft:${property("deps.minecraft_dev")}")
	implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")
	include(implementation("com.moulberry:mixinconstraints:1.0.9")!!)

	compileOnly("com.terraformersmc:biolith-fabric:${property("deps.biolith")}")
	val modonomicon = property("deps.modonomicon").toString().split('-')
	compileOnly("com.klikli_dev:modonomicon-${modonomicon[0]}-fabric:${modonomicon[1]}")

}


fletchingTable {
	mixins.create("main") { // Name should match an existing source set
		// Default matches the default value in the annotation
		mixin("default", "stellarity.mixins.json")
	}
	mixins.create("client") { // Name should match an existing source set
		// Default matches the default value in the annotation
		mixin("default", "stellarity.client.mixins.json") {
			env("CLIENT", "dev.coder2195.stellarity.client.mixin")
		}
	}
	fabric /* or neoforge { } */ {
		applyMixinConfig = false
	}
}



loom {
	splitEnvironmentSourceSets()

	mods {
		create(project.property("mod.id") as String) {
			sourceSet(sourceSets["main"])
			sourceSet(sourceSets["client"])
		}
	}


	fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json") // Useful for interface injection
	accessWidenerPath = rootProject.file("src/main/resources/${modId}.accesswidener")

	decompilerOptions.named("vineflower") {
		options.put("mark-corresponding-synthetics", "1") // Adds names to lambdas - useful for mixins
	}

	runConfigs["client"].apply {
		programArguments.addAll("--username", "Coder2195", "--uuid", "12f1e56e-9fad-4371-9d1f-a18bf67f6f13")
	}

	runConfigs["server"].apply {
		runDirectory.file("./serverrun")
	}


	runConfigs.all {
		generateRunConfig = true
		jvmArguments.addAll("-Dmixin.debug.export=true", "-XX:+AllowEnhancedClassRedefinition")
	}
}

fabricApi {
	configureDataGeneration {
		client = true

		dependencies {
			val modonomicon = property("deps.modonomicon").toString().split('-')
//			implementation("com.klikli_dev:modonomicon-${modonomicon[0]}-fabric:${modonomicon[1]}") { isTransitive = false }
		}
	}
}

tasks.withType<ProcessResources> {
	inputs.property("id", project.property("mod.id"))
	inputs.property("name", project.property("mod.name"))
	inputs.property("version", project.property("mod.version"))
	inputs.property("minecraft", project.property("mod.mc_dep"))
	inputs.property("fabric_api", project.property("deps.fabric_api"))
	inputs.property("biolith", project.property("deps.biolith"))
	inputs.property("modonomicon", project.property("deps.modonomicon"))

	val props = mapOf(
		"id" to project.property("mod.id"),
		"name" to project.property("mod.name"),
		"version" to project.property("mod.version"),
		"minecraft" to project.property("mod.mc_dep"),
		"fabric_api" to project.property("deps.fabric_api"),
		"biolith" to project.property("deps.biolith"),
		"modonomicon" to project.property("deps.modonomicon")
	)

	filesMatching("fabric.mod.json") { expand(props) }


	val mixinJava = "JAVA_${requiredJava.majorVersion}"
	filesMatching("*.mixins.json") { expand("java" to mixinJava) }

	doLast {
		fileTree("${layout.buildDirectory.get()}/resources/")
			.matching { include("**/*.json", "**/*.mcmeta", "*.json") }
			.forEach {
				it.writeText(JsonOutput.toJson(JsonSlurper().parse(it)))
			}
	}
}


java {
	withSourcesJar()
	targetCompatibility = requiredJava
	sourceCompatibility = requiredJava
}

val sourcesJar = tasks.named("sourcesJar", Jar::class)


//val generatePackageInfos = tasks.register("generatePackageInfos") {
//	description = "Generate package infos across"
//	val srcDir = file("src/main/java")
//	val outDir = layout.buildDirectory.dir("generated/sources/packageInfo/main/java").get().asFile
//	inputs.dir(srcDir)
//	outputs.dir(outDir)
//
//	doLast {
//		srcDir.walk().forEach { file ->
//			if (file.isDirectory) {
//				val javaFiles = file.listFiles(FileFilter { f -> f.name.endsWith(".java") })
//				if (javaFiles != null && javaFiles.isNotEmpty()) {
//					val relPath = srcDir.toPath().relativize(file.toPath()).toString().replace('\\', '/')
//					val pkgName = relPath.replace('/', '.')
//					val pkgInfo = File(outDir, "$relPath/package-info.java")
//					pkgInfo.parentFile.mkdirs()
//					pkgInfo.writeText("""@NullMarked
//package $pkgName;
//
//import org.jspecify.annotations.NullMarked;
//""")
//				}
//			}
//		}
//	}
//}
//
//val generatePackageInfosClient = tasks.register("generatePackageInfos") {
//	description = "Generate package infos across"
//	val srcDir = file("src/main/java")
//	val outDir = layout.buildDirectory.dir("generated/sources/packageInfo/main/java").get().asFile
//	inputs.dir(srcDir)
//	outputs.dir(outDir)
//
//	doLast {
//		srcDir.walk().forEach { file ->
//			if (file.isDirectory) {
//				val javaFiles = file.listFiles(FileFilter { f -> f.name.endsWith(".java") })
//				if (javaFiles != null && javaFiles.isNotEmpty()) {
//					val relPath = srcDir.toPath().relativize(file.toPath()).toString().replace('\\', '/')
//					val pkgName = relPath.replace('/', '.')
//					val pkgInfo = File(outDir, "$relPath/package-info.java")
//					pkgInfo.parentFile.mkdirs()
//					pkgInfo.writeText("""@NullMarked
//package $pkgName;
//
//import org.jspecify.annotations.NullMarked;
//""")
//				}
//			}
//		}
//	}
//}
//
//rootProject.sourceSets.getByName("main").java.srcDir(generatePackageInfos)
//rootProject.sourceSets.getByName("client").java.srcDir(generatePackageInfosClient)
//tasks.named("ideaSyncTask") { dependsOn(generatePackageInfos) }

tasks {
	// Builds the version into a shared folder in `build/libs/${mod version}/`
	register<Copy>("buildAndCollect") {
		description = "No clue"
		group = "build"
		from(jar.map { it.archiveFile }, sourcesJar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
		dependsOn("build")
	}

	build {
		dependsOn("validateAccessWidener")
	}
}



// Publishes builds to Modrinth and Curseforge with changelog from the CHANGELOG.md file
// Publishing using publishMods task
publishMods {
	file = tasks.jar.map { it.archiveFile.get() }
	displayName = "${property("mod.name")} ${property("mod.version")}"
	version = property("mod.version") as String
	changelog = rootProject.file("CHANGELOG.md").readText()
	type = STABLE
	modLoaders.add("fabric")

	dryRun = !env.isPresent("MODRINTH_TOKEN")
		|| !env.isPresent("CURSEFORGE_TOKEN")

	modrinth {
		projectId = property("publish.modrinth") as String
		accessToken = env.fetch("MODRINTH_TOKEN", "")
		minecraftVersions.addAll(property("mod.mc_targets").toString().split(' '))
		type = ALPHA

		environment = CLIENT_AND_SERVER

		requires("fabric-api")
		optional("biolith")
		optional("modonomicon")
		embeds("stellarity-x-nullscape")
	}

	curseforge {
		projectId = property("publish.curseforge") as String
		accessToken = env.fetch("CURSEFORGE_TOKEN", "")
		minecraftVersions.addAll(property("mod.mc_targets").toString().split(' '))
		requires("fabric-api")
		optional("biolith")
		optional("modonomicon")
		
		client = true
		server = true

		changelogType = "markdown"
	}
}
