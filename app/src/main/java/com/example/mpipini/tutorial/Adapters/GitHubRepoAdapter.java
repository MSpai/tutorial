package com.example.mpipini.tutorial.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mpipini.tutorial.Objects.GitHubRepo;
import com.example.mpipini.tutorial.R;

import java.util.List;

/**
 * Created by Maria Spai on 09/11/2017.
 */
public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.MyViewHolder> {

    private List<GitHubRepo> gitRepoList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView id, name;

        public MyViewHolder(View view) {
            super(view);
            id = (TextView) view.findViewById(R.id.title);
            name = (TextView) view.findViewById(R.id.genre);
        }
    }


    public GitHubRepoAdapter(List<GitHubRepo> gitList) {
        this.gitRepoList = gitList;
    }

    @Override
    public GitHubRepoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        return new GitHubRepoAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GitHubRepoAdapter.MyViewHolder holder, int position) {
        GitHubRepo gitHubRepo = gitRepoList.get(position);
        holder.id.setText(String.valueOf(gitHubRepo.getId()));
        holder.name.setText(gitHubRepo.getName());

    }

    @Override
    public int getItemCount() {
        return gitRepoList.size();
    }
}
