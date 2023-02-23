package nei.demo.modalinsuspend.ui.dialog_fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import nei.demo.modalinsuspend.data.Action
import nei.demo.modalinsuspend.databinding.BottomSheetActionBinding
import nei.demo.modalinsuspend.ui.adapter.ActionsAdapter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ActionBottomSheetDialogFragment(
    private val actions: List<Action>,
    private val onDismiss: (Action?) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = BottomSheetActionBinding.inflate(
        LayoutInflater.from(requireContext()),
        container,
        false
    ).apply {
        recycler.layoutManager = object : LinearLayoutManager(activity) {
            override fun canScrollVertically() = false
        }
        recycler.adapter = ActionsAdapter(actions) {
            result = it
            dismiss()
        }
    }.root

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismiss.invoke(result)
    }

    private var result: Action? = null

    companion object {
        private const val TAG = "ActionBSDF"

        suspend fun showActionBottomSheetDialogFragment(
            activity: AppCompatActivity,
            actions: List<Action>
        ): Action? = suspendCoroutine { continuation ->
            val modal = ActionBottomSheetDialogFragment(actions) {
                continuation.resume(it)
            }
            modal.show(activity.supportFragmentManager, TAG)
        }
    }
}