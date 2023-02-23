package nei.demo.modalinsuspend.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import nei.demo.modalinsuspend.data.Action
import nei.demo.modalinsuspend.databinding.ItemActionBinding

class ActionsAdapter(
    private val actions: List<Action>,
    private val onActionClick: (Action) -> Unit
) : RecyclerView.Adapter<ActionsAdapter.ViewHolder>() {

    override fun getItemCount(): Int = actions.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val action = actions[position]
        holder.binding.root.also { button ->
            button.text = action.label
            button.setOnClickListener { onActionClick.invoke(action) }
        }
    }

    inner class ViewHolder(val binding: ItemActionBinding) : RecyclerView.ViewHolder(binding.root)

}