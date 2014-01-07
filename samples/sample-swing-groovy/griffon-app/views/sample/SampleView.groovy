package sample

import griffon.core.artifact.GriffonView
import org.codehaus.griffon.core.compile.ArtifactProviderFor

import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE

@ArtifactProviderFor(GriffonView)
class SampleView {
    FactoryBuilderSupport builder                                                              //<1>
    SampleModel model                                                                          //<1>

    void initUI() {
        builder.application(title: application.applicationConfiguration['application.title'],  //<2>
            id: 'mainWindow', size: [320, 160], defaultCloseOperation: DO_NOTHING_ON_CLOSE,
            iconImage: imageIcon('/griffon-icon-48x48.png').image,
            iconImages: [imageIcon('/griffon-icon-48x48.png').image,
                imageIcon('/griffon-icon-32x32.png').image,
                imageIcon('/griffon-icon-16x16.png').image],) {
            gridLayout(cols: 1, rows: 3)
            label(application.messageSource.getMessage('name.label'))
            textField(text: bind(target: model, 'input'))                                      //<3>
            button(sayHelloAction)                                                             //<4>
        }
    }
}
