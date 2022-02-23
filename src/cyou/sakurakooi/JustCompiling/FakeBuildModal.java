package cyou.sakurakooi.JustCompiling;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

class FakeBuildModal extends Task.Modal {
    private final Project project;

    public FakeBuildModal(Project project) {
        super(project, "Building Project", true);
        this.project = project;
    }

    public void run(@NotNull ProgressIndicator indicator) {
        int progress = 0;
        indicator.setText("Copying resources... [" + project.getName() + "]");
        for (int i = 0; i < 10; i++) {
            indicator.setFraction(progress++ / 100.0f);
            if (safeSleep(indicator, 500)) return;
        }

        indicator.setText("Parsing java... [" + project.getName() + "]");
        for (int i = 0; i < 30; i++) {
            indicator.setFraction(progress++ / 100.0f);
            if (safeSleep(indicator, 1000)) return;
        }

        indicator.setText("Compiling... [" + project.getName() + "]");
        for (int i = 0; i < 50; i++) {
            indicator.setFraction(progress++ / 100.0f);
            if (safeSleep(indicator, 1000)) return;
        }

        indicator.setText("Saving caches... [" + project.getName() + "]");
        for (int i = 0; i < 10; i++) {
            indicator.setFraction(progress++ / 100.0f);
            if (safeSleep(indicator, 1000)) return;
        }
        indicator.setIndeterminate(true);
        while(true) {
            if (safeSleep(indicator, 1000)) return;
        }
    }

    private boolean safeSleep(ProgressIndicator indicator, long ms) {
        if (indicator.isCanceled())
            return true;

        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            return true;
        }
        return false;
    }
}
