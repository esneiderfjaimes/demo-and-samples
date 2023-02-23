package nei.demo.modalinsuspend.data

data class Action(val label: String, val type: String)

val actions = listOf(
    Action("Delete photo", "delete"),
    Action("Take photo", "take"),
    Action("Choose photo", "choose")
)