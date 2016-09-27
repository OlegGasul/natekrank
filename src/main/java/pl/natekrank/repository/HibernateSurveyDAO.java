package pl.natekrank.repository;

import org.hibernate.CacheMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import pl.natekrank.model.Survey;

import java.util.List;

@Service
public class HibernateSurveyDAO extends HibernateDaoSupport implements SurveyDAO {
    public HibernateSurveyDAO(SessionFactory sessionfactory) {
        setSessionFactory(sessionfactory);
    }

    @Autowired
    private TaskDAO taskDAO;

    @Override
    public List<Survey> getAllSurveys() {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        List<Survey> list = session.createCriteria(Survey.class).addOrder(Order.asc("id")).list();
        for(Survey survey : list) {
            Hibernate.initialize(survey.getSurveyAnswers());
        }

        if (session.isOpen()) {
            session.close();
        }

        return list;
    }

    @Override
    public Survey save(Survey survey) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        survey.setTask(taskDAO.getTask(survey.getTaskId()));

        session.saveOrUpdate(survey);
        session.flush();

        if (session.isOpen()) {
            session.close();
        }

        return survey;
    }

    public Survey getSurvey(Long id) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        Survey survey = (Survey) session.createCriteria(Survey.class).add(Restrictions.eq("id", id)).setCacheable(false).uniqueResult();
        Hibernate.initialize(survey.getSurveyAnswers());

        if (session.isOpen()) {
            session.close();
        }

        return survey;
    }

    public Survey getSurvey(String testKey) {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        Survey survey = (Survey) session.createCriteria(Survey.class).add(Restrictions.eq("surveyKey", testKey)).setCacheable(false).uniqueResult();
        Hibernate.initialize(survey.getSurveyAnswers());

        if (session.isOpen()) {
            session.close();
        }

        return survey;
    }

    @Override
    public List<Survey> getQueueSurveys() {
        Session session = getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);

        List<Survey> list = session.createCriteria(Survey.class)
            .add(Restrictions.eq("sent", false))
            .addOrder(Order.asc("id"))
            .list();

        if (session.isOpen()) {
            session.close();
        }

        return list;
    }
}
