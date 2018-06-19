package com.hieupham.data.migration;

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory;
import android.arch.persistence.room.testing.MigrationTestHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.hieupham.data.source.local.api.DatabaseManager;
import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by hieupham on 5/28/18.
 */

@RunWith(AndroidJUnit4.class)
public class MigrationTest {

    @Rule
    public MigrationTestHelper migrationTestHelper =
            new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                    DatabaseManager.class.getCanonicalName(),
                    new FrameworkSQLiteOpenHelperFactory());

    @Test
    public void verifyMigrationFrom1_2() throws IOException {
    }

    @Test
    public void verifyMigrationFrom2_3() throws IOException {

    }

    @Test
    public void verifyMigrationFrom3_4() throws IOException {

    }
}
