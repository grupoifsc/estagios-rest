package com.github.projetoifsc.estagios.app.model.response.wrapper;

/**
 * Interface for JSend Json response Standardization
 * <br><br>See project: <a href="https://github.com/omniti-labs/jsend">github.com/omniti-labs/jsend</a>
 */
interface JSendResponse {

    String getStatus();
    Object getData();

}
