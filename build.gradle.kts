task<Delete>("clean") {
    delete(rootProject.buildFile)
}

subprojects {
    apply(plugin = Plugins.ktlint)
}
