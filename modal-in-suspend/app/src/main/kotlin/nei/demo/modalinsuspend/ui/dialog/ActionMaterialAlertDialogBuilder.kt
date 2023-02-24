package nei.demo.modalinsuspend.ui.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import nei.demo.modalinsuspend.data.Action
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun showActionMaterialAlertDialogBuilder(
    ctx: Context,
    actions: List<Action>
): Action? = suspendCoroutine { continuation ->
    var result: Action? = null
    MaterialAlertDialogBuilder(ctx)
        .setItems(actions.map { it.label }.toTypedArray()) { _, position ->
            result = actions[position]
        }
        .setNegativeButton("Cancel", null)
        .setOnDismissListener {
            continuation.resume(result)
        }
        .show()
}