package main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gameattempt.R

class ScoreAdapter(
    var highScoreList: List<Leaderboard>
) : RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder>() {

    inner class ScoreViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var tvNameLabel = itemView.findViewById<TextView>(R.id.tvNameLabel)
        var tvScoreLabel = itemView.findViewById<TextView>(R.id.tvScoreLabel)
        var tvDifficultyLabel = itemView.findViewById<TextView>(R.id.tvDifficultyLabel)
        var tvName = itemView.findViewById<TextView>(R.id.tvName)
        var tvScore = itemView.findViewById<TextView>(R.id.tvScore)
        var tvDifficulty = itemView.findViewById<TextView>(R.id.tvDifficulty)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreViewHolder {
    //called when recyclerView needs a new viewHolder
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.score_board , parent , false)
//        return ScoreViewHolder(view)
        val layoutInflater = LayoutInflater.from(parent.context)
        val view= layoutInflater.inflate(R.layout.score_board, parent , false)
        return ScoreViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreViewHolder, position: Int) {
        holder.itemView.apply {
            holder.tvName.text = highScoreList[position].name
            holder.tvDifficulty.text = highScoreList[position].level
            holder.tvScore.text = highScoreList[position].highScore
        }
    }

    override fun getItemCount(): Int {
        return highScoreList.size
    }

}

