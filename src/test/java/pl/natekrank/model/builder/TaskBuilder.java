package pl.natekrank.model.builder;

import pl.natekrank.model.Question;
import pl.natekrank.model.Role;
import pl.natekrank.model.Task;

import java.util.Date;
import java.util.List;

/**
 * Created by ievgenii on 26.09.16.
 */


public class TaskBuilder implements Builder<Task> {
    long id;
    long user_id;
    private Date created;
    private int daysExpired;
    private String name;
    private List<Question> questions;


    public TaskBuilder withId(long id) {
        this.id = id;
        return this;
    }


    public TaskBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TaskBuilder withQuestions(List<Question> questions) {
        this.questions = questions;
        return this;
    }
    @Override
    public Task build() {
        Task task = new Task();
        task.setId(id);
        task.setCreated(created);
        task.setDaysExpired(daysExpired);
        task.setName(name);
        task.setQuestions(questions);
        return task;
    }
}


