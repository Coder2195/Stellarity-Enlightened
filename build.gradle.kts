plugins {
	id("net.fabricmc.fabric-loom")
	id("dev.kikugie.fletching-table.fabric") version "0.1.0-alpha.22"
	id("me.modmuss50.mod-publish-plugin") version "1.1.0"
	// `maven-publish`
	kotlin("jvm") version "2.2.20"
	id("com.google.devtools.ksp") version "2.2.20-2.0.4"
}

version = "${property("mod.version")}+${stonecutter.current.version}"
base.archivesName = property("mod.id") as String

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

	minecraft("com.mojang:minecraft:${stonecutter.current.version}")
	implementation("net.fabricmc:fabric-loader:${property("deps.fabric_loader")}")
	implementation("net.fabricmc.fabric-api:fabric-api:${property("deps.fabric_api")}")

	compileOnly("com.terraformersmc:biolith-fabric:${property("deps.biolith")}")
	val modonomicon = property("deps.modonomicon").toString().split('-')
	compileOnly("com.klikli_dev:modonomicon-${modonomicon[0]}-fabric:${modonomicon[1]}") { isTransitive = false }

}

stonecutter {

}


fletchingTable {
	mixins.create("main") { // Name should match an existing source set
		// Default matches the default value in the annotation
		mixin("default", "stellarity.mixins.json")
	}
	mixins.create("client") { // Name should match an existing source set
		// Default matches the default value in the annotation
		mixin("default", "stellarity.client.mixins.json") {
			env("CLIENT", "prismatic.shards.stellarity.client.mixin")
		}
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
	accessWidenerPath = sc.process(rootProject.file("src/main/resources/stellarity.accesswidener"), "build/dev.aw")
	file("build/generated/stonecutter/main/resources/stellarity.accesswidener").let {
		if (it.exists()) accessWidenerPath = it
	}

	decompilerOptions.named("vineflower") {
		options.put("mark-corresponding-synthetics", "1") // Adds names to lambdas - useful for mixins
	}

	runConfigs["client"].apply {
		programArgs("--username=StellarityDev")
	}

	runConfigs["server"].apply {
		runDir = "./serverrun"
	}


	runConfigs.all {
		ideConfigGenerated(true)
		vmArgs("-Dmixin.debug.export=true -XX:+AllowEnhancedClassRedefinition")
	}
}



fabricApi {
	configureDataGeneration {
		client = true
		modId = "stellarity"
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
}


java {
	withSourcesJar()
	targetCompatibility = requiredJava
	sourceCompatibility = requiredJava
}

val sourcesJar = tasks.named("sourcesJar", Jar::class)

tasks {
	// Builds the version into a shared folder in `build/libs/${mod version}/`
	register<Copy>("buildAndCollect") {
		group = "build"
		from(jar.map { it.archiveFile }, sourcesJar.map { it.archiveFile })
		into(rootProject.layout.buildDirectory.file("libs/${project.property("mod.version")}"))
		dependsOn("build")
	}

	build {
		dependsOn("validateAccessWidener")
	}

	stonecutterGenerate {
		dependsOn("validateAccessWidener")
	}


}


// Publishes builds to Modrinth and Curseforge with changelog from the CHANGELOG.md file
// Publishing using publishMods task
publishMods {
	file = tasks.jar.map { it.archiveFile.get() }
	displayName = "${property("mod.name")} ${property("mod.version")} for ${property("mod.mc_title")}"
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

		requires("fabric-api")
		optional("biolith")
		optional("modonomicon")
	}

	curseforge {
		projectId = property("publish.curseforge") as String
		accessToken = env.fetch("CURSEFORGE_TOKEN", "")
		minecraftVersions.addAll(property("mod.mc_targets").toString().split(' '))
		requires("fabric-api")
		optional("biolith")
		optional("modonomicon")
	}
}


/*
// Publishes builds to a maven repository under `com.example:template:0.1.0+mc`
publishing {
    repositories {
        maven("https://maven.example.com/releases") {
            name = "myMaven"
            // To authenticate, create `myMavenUsername` and `myMavenPassword` properties in your Gradle home properties.
            // See https://stonecutter.kikugie.dev/wiki/tips/properties#defining-properties
            credentials(PasswordCredentials::class.java)
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }

    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "${property("mod.group")}.${property("mod.id")}"
            artifactId = property("mod.id") as String
            version = project.version

            from(components["java"])
        }
    }
}
*/
