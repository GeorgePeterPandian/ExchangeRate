tasks.register("Clean", Delete::class.java) {
    delete(rootProject.buildDir)
}