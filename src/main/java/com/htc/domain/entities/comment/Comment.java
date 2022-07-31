package com.htc.domain.entities.comment;

import com.htc.domain.entities.task.Task;
import com.htc.domain.entities.user.User;
import com.htc.domain.entities.utility.parameters.DateCreated;
import com.htc.domain.entities.utility.parameters.Id;

/**
 * Комментарий.
 */
public interface Comment {
  /**
   * Получить идентификатор комментария.
   *
   * @see Id#create(Long)
   *
   * @return id идентификатор
   */
  Id getId();

  /**
   * Получить дату создания.
   *
   * @see DateCreated#create()
   *
   * @return dateCreated дата создания
   */
  DateCreated getDateCreated();

  /**
   * Получить {@link User автора}.
   *
   * @return user автор
   */
  User getAuthor();

  /**
   * Получить текст комментария.
   *
   * @return message текст комментария
   */
  String getMessage();

  /**
   * Получить {@link Task задача}.
   *
   * @return task задача
   */
  Task getTask();
}
