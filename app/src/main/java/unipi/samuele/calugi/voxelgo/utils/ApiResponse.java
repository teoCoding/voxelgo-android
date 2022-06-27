package unipi.samuele.calugi.voxelgo.utils;

public class ApiResponse {

    private String code;
    private Model[] models;

    public String getCode() {
        return code;
    }

    public Model[] getModels() {
        return models;
    }

    public static class Model {

        private String model_name;
        private String model_url_name;
        private int model_rarity;

        public String getModel_name() {
            return model_name;
        }

        public int getModel_rarity() {
            return model_rarity;
        }

        public String getModel_url_name() {
            return model_url_name;
        }

    }
}