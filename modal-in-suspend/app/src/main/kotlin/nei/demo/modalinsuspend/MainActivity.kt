package nei.demo.modalinsuspend

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import nei.demo.modalinsuspend.data.actions
import nei.demo.modalinsuspend.databinding.ActivityMainBinding
import nei.demo.modalinsuspend.ui.dialog.showActionBottomSheetDialog
import nei.demo.modalinsuspend.ui.dialog.showActionMaterialAlertDialogBuilder
import nei.demo.modalinsuspend.ui.dialog_fragment.ActionBottomSheetDialogFragment.Companion.showActionBottomSheetDialogFragment
import nei.demo.modalinsuspend.utils.toast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.actionBottomSheetDialogBtn.launchOnClick {
            val actionSelected = showActionBottomSheetDialog(this@MainActivity, actions)
            addToast("Action selected ${actionSelected?.type ?: "none"}")
        }

        binding.actionBottomSheetDialogFragmentBtn.launchOnClick {
            val actionSelected = showActionBottomSheetDialogFragment(this@MainActivity, actions)
            addToast("Action selected ${actionSelected?.type ?: "none"}")
        }

        binding.actionMaterialAlertDialogBtn.launchOnClick {
            val actionSelected = showActionMaterialAlertDialogBuilder(this@MainActivity, actions)
            addToast("Action selected ${actionSelected?.type ?: "none"}")
        }
    }

    private inline fun View.launchOnClick(crossinline block: suspend CoroutineScope.(View) -> Unit) {
        setOnClickListener {
            lifecycleScope.launchWhenCreated { block.invoke(this, it) }
        }
    }

    private var toast: Toast? = null
    private fun addToast(msg: String) {
        toast?.cancel()
        toast = toast(msg)
        toast?.show()
    }
}