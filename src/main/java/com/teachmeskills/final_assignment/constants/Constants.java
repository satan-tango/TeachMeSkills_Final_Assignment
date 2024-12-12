package com.teachmeskills.final_assignment.constants;

public interface Constants {

    String REG_EX_NUMBERS_DOT_COMMA = "\\d.*\\d";

    String PATH_REPORT = "src/main/resources/Report";

    String UNSUPPORTED_FILE_PATH = "src/main/resources/unsupported_files";

    String VALID_CHECK_PATH = "src/main/resources/valid_data/checks";

    String VALID_INVOICE_PATH = "src/main/resources/valid_data/invoices";

    String VALID_ORDERS_PATH = "src/main/resources/valid_data/orders";

    String INVALID_CHECK_PATH = "src/main/resources/invalid_data/checks";

    String INVALID_INVOICE_PATH = "src/main/resources/invalid_data/invoices";

    String INVALID_ORDERS_PATH = "src/main/resources/invalid_data/orders";

    String PATH_LOG = "logs/log.txt";

    String LOGIN_VERIFICATION = "^[A-Za-z]([\\.A-Za-z0-9-]{1,18})([A-Za-z0-9])$";

    String PASSWORD_VERIFICATION = "^(?=.*[0-9])(?=.*[A-Z]).+$";

}
