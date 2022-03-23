package com.lunchbox.activity;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.lunchbox.database.AppDatabase;
import com.lunchbox.domain.model.User;
import com.lunchbox.repository.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@Config(manifest= Config.NONE)
public class ExampleUnitTest {

    private AppDatabase db;
    private UserRepository repository;

    @Before
    public void createDb() throws Exception {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(),
                AppDatabase.class)
                .build();
        repository = db.getRepository(UserRepository.class);

    }

    @After
    public void closeDb() throws Exception {
        db.close();
    }

    @Test
    public void addition_isCorrect() {

        //List<User> users = repository.getAll();

        repository.getAll2()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        for(User user : users)
                            repository.delete(user);
                    }
                });

/*        Callable<List<User>> callable = new Callable<List<User>>() {
            @Override
            public List<User> call() throws Exception {
                return repository.getAll();
            }
        };

        Future<List<User>> future = Executors.newSingleThreadExecutor().submit(callable);
        List<User> users = future.get();

        for(User user : users)
            repository.delete(user);

        assertEquals(4, 2 + 2);*/
    }
}