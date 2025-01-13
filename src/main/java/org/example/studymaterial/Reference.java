package org.example.studymaterial;

public abstract class Reference {
    private String title;
    private String description;
    private String link;
    private String accessRights;
    private String license;
    private boolean isDownloadable;
    private int rating;
    private String language;
    private int viewCount;
    private int downloadCount;
    private int shareCount;

    // Construtor padrão refatorado
    public Reference() {
        initializeDefaults();
    }

    // Método para inicializar os valores padrão
    private void initializeDefaults() {
        initializeBasicFields();
        initializeAccessRights();
        initializeCounts();
        initializeLanguageAndRating();
    }

    // Métodos auxiliares para inicializar os campos de forma modular
    private void initializeBasicFields() {
        this.title = "";
        this.description = null;
        this.link = "";
    }

    private void initializeAccessRights() {
        this.accessRights = "Public";
        this.license = "";
    }

    private void initializeCounts() {
        this.isDownloadable = false;
        this.viewCount = 0;
        this.downloadCount = 0;
        this.shareCount = 0;
    }

    private void initializeLanguageAndRating() {
        this.language = "Unknown";
        this.rating = 0;
    }

    // Método de encapsulamento: Acessibilidade com base nos direitos de acesso
    public boolean isAccessible() {
        return "Public".equalsIgnoreCase(accessRights);
    }

    // Método de encapsulamento: Verificar se pode ser baixado
    public boolean canBeDownloaded() {
        return isDownloadable && isAccessible();
    }

    // Método para obter o valor de isDownloadable
    public boolean getIsDownloadable() {
        return isDownloadable;
    }

    // Métodos para atualizar as contagens
    public void incrementViewCount() {
        if (viewCount < Integer.MAX_VALUE) {
            viewCount++;
        }
    }

    public void incrementDownloadCount() {
        if (downloadCount < Integer.MAX_VALUE) {
            downloadCount++;
        }
    }

    public void incrementShareCount() {
        if (shareCount < Integer.MAX_VALUE) {
            shareCount++;
        }
    }

    // Método para atualizar a classificação
    public void updateRating(int newRating) {
        if (newRating >= 1 && newRating <= 5) {
            rating = newRating;
        }
    }

    // Métodos para editar dados essenciais (Refatoração para evitar excesso de complexidade)
    public void editBasic(String title, String description, String link) {
        this.setTitle(title);
        this.setDescription(description);
        this.setLink(link);
    }

    public void editDownloadable(boolean isDownloadable) {
        this.setDownloadable(isDownloadable);
    }

    public void editAccessRights(String accessRights) {
        this.setAccessRights(accessRights);
    }

    public void editLanguageAndRating(String language, int rating) {
        this.setLanguage(language);
        this.setRating(rating);
    }

    // Getters e setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAccessRights() {
        return accessRights;
    }

    public void setAccessRights(String accessRights) {
        this.accessRights = accessRights;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public boolean isDownloadable() {
        return isDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        isDownloadable = downloadable;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }
}
