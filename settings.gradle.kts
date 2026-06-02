pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/")
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases")
	}
}

plugins {
	id("dev.kikugie.stonecutter") version "0.10-alpha.1"
}

stonecutter {
	create(rootProject) {
		// See https://stonecutter.kikugie.dev/wiki/start/#choosing-minecraft-versions
		version("26.2", "26.2-pre-3")
		vcsVersion = "26.2"
	}
}


rootProject.name = "Stellarity"