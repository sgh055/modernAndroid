package kr.co.kkyu.modernandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import kr.co.kkyu.modernandroid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//    private EditText mTodoEditText;
//    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        DataBindingUtil.setContentView(this, R.layout.activity_main);
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);

//        mTodoEditText = findViewById(R.id.todo_edit);
//        mResultTextView = findViewById(R.id.result_text);

        MainViewModel mainViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);

//        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
//                //.allowMainThreadQueries() 메인스레드 db 허용
//                .build();

        //UI 갱신
        mainViewModel.getAll().observe(this, todos -> {
//            mResultTextView.setText(todos.toString());
            activityMainBinding.resultText.setText(todos.toString());
        });

        //버튼 클릭시 DB에 insert
        activityMainBinding.addButton.setOnClickListener(view -> {
            //db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
//            new InsertAsyncTask(db.todoDao()).execute(new Todo(mTodoEditText.getText().toString()));
//            mainViewModel.insert(new Todo(mTodoEditText.getText().toString()));
            mainViewModel.insert(new Todo(activityMainBinding.todoEdit.getText().toString()));
        });
    }

//    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
//        private TodoDao mTododao;
//        //백그라운드 insert
//        public InsertAsyncTask(TodoDao tododao) {
//            this.mTododao = tododao;
//        }
//
//        @Override
//        protected Void doInBackground(Todo... todos) {
//            mTododao.insert(todos[0]);
//            return null;
//        }
//    }
}