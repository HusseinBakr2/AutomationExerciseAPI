package config;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

        public class ConfigManager {
            private static volatile ConfigManager instance;
            private final Properties properties;
            private static final String CONFIG_FILE = "/config/config.properties";
            private static final String DEFAULT_BASE_URI = "https://automationexercise.com";

            private ConfigManager() {
                properties = new Properties();
                loadProperties();
                validateBaseUri();
            }

            private void loadProperties() {
                try (InputStream inputStream = ConfigManager.class.getResourceAsStream(CONFIG_FILE)) {
                    if (inputStream == null) {
                        throw new RuntimeException("Configuration file not found: " + CONFIG_FILE);
                    }
                    properties.load(inputStream);
                } catch (IOException e) {
                    throw new RuntimeException("Failed to load configuration file: " + CONFIG_FILE, e);
                }
            }

            private void validateBaseUri() {
                String baseUri = properties.getProperty("base.uri");
                if (baseUri == null || baseUri.trim().isEmpty()) {
                    properties.setProperty("base.uri", DEFAULT_BASE_URI);
                }
            }

            public static ConfigManager getInstance() {
                if (instance == null) {
                    synchronized (ConfigManager.class) {
                        if (instance == null) {
                            instance = new ConfigManager();
                        }
                    }
                }
                return instance;
            }

            public String getProperty(String key) {
                if (key == null || key.trim().isEmpty()) {
                    throw new IllegalArgumentException("Property key cannot be null or empty");
                }
                String value = properties.getProperty(key);
                if (value == null) {
                    throw new IllegalArgumentException("Property not found for key: " + key);
                }
                return value;
            }

            public String getBaseUri() {
                return getProperty("base.uri");
            }

            // For testing purposes
            static void resetInstance() {
                instance = null;
            }
        }