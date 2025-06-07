package utils;

        public class TestDataProvider {
            public static Object[][] getCreateAccountData() {
                return new Object[][] {

                    {"john123", "john.doe@test.com", "Mr", "Pass123!", "15", "6", "1990", "John", "Doe",
                     "Tech Corp", "123 Main St", "Apt 4B", "United States", "California", "San Francisco",
                     "94105", "1234567890"},
                    {"sarah456", "sarah.smith@test.com", "Mrs", "Pass456!", "22", "8", "1985", "Sarah", "Smith",
                     "Design Inc", "456 Oak Ave", "Suite 7C", "United States", "New York", "Brooklyn",
                     "11201", "9876543210"},
                    {"emma789", "emma.wilson@test.com", "Miss", "Pass789!", "10", "3", "1995", "Emma", "Wilson",
                     "Creative LLC", "789 Pine St", "Unit 12", "United States", "Texas", "Austin",
                     "73301", "5554443333"}
                };
            }

            public static Object[][] getLoginData() {
                return new Object[][] {
                    {"email", "password", "expected_status"},
                    {"john.doe@test.com", "Pass123!", 200},
                    {"invalid@test.com", "WrongPass!", 404},
                    {"", "Pass123!", 400},
                    {"john.doe@test.com", "", 400}
                };
            }

            public static Object[][] getProductData() {
                return new Object[][] {
                    {"Blue Top", 599.00, "Women", "Tops", "Polo"},
                    {"Men Tshirt", 399.00, "Men", "Tshirts", "Polo"},
                    {"Sleeveless Dress", 899.00, "Women", "Dress", "Madame"}
                };
            }

            public static Object[][] getSearchData() {
                return new Object[][] {
                    {"search_product", "expected_count"},
                    {"dress", 5},
                    {"tshirt", 3},
                    {"jeans", 4}
                };
            }

            public static Object[][] getUpdateAccountData() {
                return new Object[][] {
                    {"firstname", "lastname", "email", "password", "title", "days", "months", "years",
                     "company", "address1", "address2", "country", "state", "city", "zipcode", "mobile_number"},
                    {"John", "Updated", "john.doe@test.com", "NewPass123!", "Mr", "15", "6", "1990",
                     "New Corp", "789 Update St", "Suite 15", "United States", "California", "Los Angeles",
                     "90001", "9998887777"}
                };
            }
        }