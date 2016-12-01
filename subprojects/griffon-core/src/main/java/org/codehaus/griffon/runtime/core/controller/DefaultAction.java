/*
 * Copyright 2008-2016 the original author or authors.
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
package org.codehaus.griffon.runtime.core.controller;

import griffon.core.artifact.GriffonController;
import griffon.core.controller.ActionManager;
import griffon.core.threading.UIThreadManager;

import javax.annotation.Nonnull;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 * @since 2.0.0
 */
public class DefaultAction extends AbstractAction {
    private final DefaultToolkitAction toolkitAction;

    public DefaultAction(@Nonnull final UIThreadManager uiThreadManager, @Nonnull final ActionManager actionManager, @Nonnull final GriffonController controller, @Nonnull final String actionName) {
        super(actionManager, controller, actionName);
        requireNonNull(uiThreadManager, "Argument 'uiThreadManager' must not be null");

        toolkitAction = new DefaultToolkitAction(new Runnable() {
            @Override
            public void run() {
                actionManager.invokeAction(controller, actionName);
            }
        });
        addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(final PropertyChangeEvent evt) {
                uiThreadManager.runInsideUIAsync(new Runnable() {
                    public void run() {
                        toolkitAction.setName(String.valueOf(evt.getNewValue()));
                    }
                });
            }
        });
    }

    @Nonnull
    public Object getToolkitAction() {
        return toolkitAction;
    }

    protected void doExecute(Object... args) {
        toolkitAction.execute();
    }

    @Override
    protected void doInitialize() {
        toolkitAction.setName(getName());
    }
}
