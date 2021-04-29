package kr.co.kkyu.modernandroid;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel; //context가 필요한 viewmodel일 경우
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                .build();
    }

    public LiveData<List<Todo>> getAll() {
        return db.todoDao().getAll();
    }

    public void insert(Todo todo) {
        new InsertAsyncTask(db.todoDao()).execute(todo);
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDao mTododao;
        //백그라운드 insert
        public InsertAsyncTask(TodoDao tododao) {
            this.mTododao = tododao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTododao.insert(todos[0]);
            return null;
        }
    }
}
