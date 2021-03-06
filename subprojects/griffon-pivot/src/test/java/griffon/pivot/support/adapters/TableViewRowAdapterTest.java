/*
 * Copyright 2008-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.pivot.support.adapters;

import griffon.core.CallableWithArgs;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class TableViewRowAdapterTest {
    private TableViewRowAdapter adapter = new TableViewRowAdapter();

    @Test
    public void testRowsCleared() {
        final boolean[] invoked = new boolean[1];
        CallableWithArgs<Void> callable = new CallableWithArgs<Void>() {
            public Void call(Object... args) {
                invoked[0] = true;
                return null;
            } 
        };

        assertNull(adapter.getRowsCleared());
        adapter.rowsCleared(null);
        assertFalse(invoked[0]);

        adapter.setRowsCleared(callable);
        adapter.rowsCleared(null);
        assertTrue(invoked[0]);
    }

    @Test
    public void testRowsSorted() {
        final boolean[] invoked = new boolean[1];
        CallableWithArgs<Void> callable = new CallableWithArgs<Void>() {
            public Void call(Object... args) {
                invoked[0] = true;
                return null;
            } 
        };

        assertNull(adapter.getRowsSorted());
        adapter.rowsSorted(null);
        assertFalse(invoked[0]);

        adapter.setRowsSorted(callable);
        adapter.rowsSorted(null);
        assertTrue(invoked[0]);
    }

    @Test
    public void testRowsRemoved() {
        final boolean[] invoked = new boolean[1];
        CallableWithArgs<Void> callable = new CallableWithArgs<Void>() {
            public Void call(Object... args) {
                invoked[0] = true;
                return null;
            } 
        };

        assertNull(adapter.getRowsRemoved());
        adapter.rowsRemoved(null, 0, 0);
        assertFalse(invoked[0]);

        adapter.setRowsRemoved(callable);
        adapter.rowsRemoved(null, 0, 0);
        assertTrue(invoked[0]);
    }

    @Test
    public void testRowUpdated() {
        final boolean[] invoked = new boolean[1];
        CallableWithArgs<Void> callable = new CallableWithArgs<Void>() {
            public Void call(Object... args) {
                invoked[0] = true;
                return null;
            } 
        };

        assertNull(adapter.getRowUpdated());
        adapter.rowUpdated(null, 0);
        assertFalse(invoked[0]);

        adapter.setRowUpdated(callable);
        adapter.rowUpdated(null, 0);
        assertTrue(invoked[0]);
    }

    @Test
    public void testRowInserted() {
        final boolean[] invoked = new boolean[1];
        CallableWithArgs<Void> callable = new CallableWithArgs<Void>() {
            public Void call(Object... args) {
                invoked[0] = true;
                return null;
            } 
        };

        assertNull(adapter.getRowInserted());
        adapter.rowInserted(null, 0);
        assertFalse(invoked[0]);

        adapter.setRowInserted(callable);
        adapter.rowInserted(null, 0);
        assertTrue(invoked[0]);
    }

}
