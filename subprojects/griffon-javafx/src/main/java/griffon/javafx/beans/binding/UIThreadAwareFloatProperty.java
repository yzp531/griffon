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
package griffon.javafx.beans.binding;

import javafx.beans.InvalidationListener;
import javafx.beans.property.FloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.annotation.Nonnull;

import static java.util.Objects.requireNonNull;
import static javafx.application.Platform.isFxApplicationThread;
import static javafx.application.Platform.runLater;

/**
 * @author Andres Almiray
 * @since 2.9.0
 */
class UIThreadAwareFloatProperty extends FloatProperty implements UIThreadAware {
    private final FloatProperty delegate;

    UIThreadAwareFloatProperty(@Nonnull FloatProperty delegate) {
        this.delegate = requireNonNull(delegate, "Argument 'delegate' must not be null");
    }

    @Override
    public float get() {
        return delegate.get();
    }

    @Override
    public void set(final float value) {
        if (isFxApplicationThread()) {
            delegate.set(value);
        } else {
            runLater(() -> delegate.set(value));
        }
    }

    @Override
    public void bind(ObservableValue<? extends Number> observable) {
        delegate.bind(observable);
    }

    @Override
    public void unbind() {
        delegate.unbind();
    }

    @Override
    public boolean isBound() {
        return delegate.isBound();
    }

    @Override
    public Object getBean() {
        return delegate.getBean();
    }

    @Override
    public String getName() {
        return delegate.getName();
    }

    @Override
    public void addListener(ChangeListener<? super Number> listener) {
        if (listener instanceof UIThreadAware) {
            delegate.addListener(listener);
        } else {
            delegate.addListener(new UIThreadAwareChangeListener<>(listener));
        }
    }

    @Override
    public void removeListener(ChangeListener<? super Number> listener) {
        if (listener instanceof UIThreadAware) {
            delegate.removeListener(listener);
        } else {
            delegate.removeListener(new UIThreadAwareChangeListener<>(listener));
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        if (listener instanceof UIThreadAware) {
            delegate.addListener(listener);
        } else {
            delegate.addListener(new UIThreadAwareInvalidationListener(listener));
        }
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        if (listener instanceof UIThreadAware) {
            delegate.removeListener(listener);
        } else {
            delegate.removeListener(new UIThreadAwareInvalidationListener(listener));
        }
    }

    @Override
    public boolean equals(Object o) {
        return this == o || delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + delegate.toString();
    }
}
