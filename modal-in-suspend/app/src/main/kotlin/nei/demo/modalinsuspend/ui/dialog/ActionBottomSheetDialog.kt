package nei.demo.modalinsuspend.ui.dialog

import android.app.Activity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import nei.demo.modalinsuspend.data.Action
import nei.demo.modalinsuspend.databinding.BottomSheetActionBinding
import nei.demo.modalinsuspend.ui.adapter.ActionsAdapter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun showActionBottomSheetDialog(
    activity: Activity,
    actions: List<Action>
): Action? = suspendCoroutine { continuation ->
    val dialog = BottomSheetDialog(activity)
    var result: Action? = null
    val sheetBinding =
        BottomSheetActionBinding.inflate(activity.layoutInflater, null, false)
            .apply {
                recycler.layoutManager = object : LinearLayoutManager(activity) {
                    override fun canScrollVertically() = false
                }
                recycler.adapter = ActionsAdapter(actions) {
                    result = it
                    dialog.dismiss()
                }
            }
    dialog.setContentView(sheetBinding.root)
    dialog.setOnDismissListener {
        continuation.resume(result)
    }
    dialog.show()
}