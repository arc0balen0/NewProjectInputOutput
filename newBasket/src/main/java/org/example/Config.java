package org.example;

class Config {
    private boolean loadEnabled;
    private String loadFileName;
    private String loadFormat;
    private boolean saveEnabled;
    private String saveFileName;
    private String saveFormat;
    private boolean logEnabled;
    private String logFileName;

    public Config(boolean loadEnabled, String loadFileName, String loadFormat, boolean saveEnabled, String saveFileName, String saveFormat, boolean logEnabled, String logFileName) {
        this.loadEnabled = loadEnabled;
        this.loadFileName = loadFileName;
        this.loadFormat = loadFormat;
        this.saveEnabled = saveEnabled;
        this.saveFileName = saveFileName;
        this.saveFormat = saveFormat;
        this.logEnabled = logEnabled;
        this.logFileName = logFileName;
    }

    public boolean isLoadEnabled() {
        return loadEnabled;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public String getLoadFormat() {
        return loadFormat;
    }

    public boolean isSaveEnabled() {
        return saveEnabled;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public String getSaveFormat() {
        return saveFormat;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public String getLogFileName() {
        return logFileName;
    }
}
