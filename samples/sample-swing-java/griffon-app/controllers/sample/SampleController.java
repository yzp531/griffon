package sample;

import griffon.core.GriffonApplication;
import griffon.core.artifact.GriffonController;
import org.codehaus.griffon.core.compile.ArtifactProviderFor;
import org.codehaus.griffon.runtime.core.artifact.AbstractGriffonController;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.swing.JOptionPane;
import java.awt.Window;

@ArtifactProviderFor(GriffonController.class)
public class SampleController extends AbstractGriffonController {
    private SampleModel model;                                           //<1>

    @Inject
    private SampleService sampleService;                                 //<2>

    @Inject
    public SampleController(@Nonnull GriffonApplication application) {
        super(application);
    }

    public void setModel(SampleModel model) {
        this.model = model;
    }

    public void sayHello() {                                             //<3>
        final String result = sampleService.sayHello(model.getInput());
        runInsideUIAsync(new Runnable() {                                //<4>
            @Override
            public void run() {
                JOptionPane.showMessageDialog(
                    (Window) getApplication().getWindowManager().getStartingWindow(),
                    result,
                    getApplication().getMessageSource().getMessage("dialog.title", "Hello"),
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
