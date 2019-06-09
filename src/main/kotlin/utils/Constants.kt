package utils

object Constants {
    /**Порт, на котором запускается джавалин */
    const val PORT = 1807

    /**Путь до базы (относительный).Подразуменвается, что файл лежит в папке проекта */
    const val DB_PATH = "jdbc:h2:store"

    //HTTP Response Status codes
    /**Отдается, когда все хорошо */
    const val OK_200 = 200
    /**Отдается, когда мы успешно создали новую запись в БД */
    const val CREATED_201 = 201
    /**Отдается, когда не найден путь или не найдена запись в базе */
    const val NOT_FOUND_404 = 404
    /**Отдается, когда на сервере произошла какая-либо ошибка */
    const val INTERNAL_SERVER_ERROR_500 = 500
    /**Отдается, когда клиент неправильно оформил запрос */
    const val BAD_REQUEST_400 = 400
    /**Отдается, когда клиент отсутствует в базе */
    const val UNAUTHORIZED_401 = 401
    /**Отдается, когда клиент отсутствует в базе */
    const val CONFLICT_409 = 409
}