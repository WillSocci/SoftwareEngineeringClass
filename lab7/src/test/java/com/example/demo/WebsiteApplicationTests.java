package com.example.demo;

import com.example.demo.controller.HomeController;
import com.example.demo.model.Count;
import com.example.demo.model.MyString;
import com.example.demo.model.GetQuestion;
import com.example.demo.model.questions.QuestionTrueFalse;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;
import org.springframework.ui.ExtendedModelMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class WebsiteApplicationTests {

    private final HomeController controller = new HomeController();

    @Test
    void contextLoads() {
    }

    @Test
    void testHomeReturnsHomeView() {
        assertThat(controller.home(), is("home"));
    }

    @Test
    void testGreetingFormAddsAttributes() {
        Model model = new ExtendedModelMap();
        controller.greetingForm(model);
        assertThat(model.containsAttribute("greeting"), is(true));
        assertThat(model.containsAttribute("count"), is(true));
    }

    @Test
    void testQuestionFormInitialCount() {
        Model model = new ExtendedModelMap();
        controller.questionForm(model);
        Count count = (Count) model.getAttribute("count");
        assertThat(count, notNullValue());
        assertThat(count.getCount(), is(1));
    }

    @Test
    void testCorrectAnswerIncrementsCorrectCount() {
        Count count = new Count();
        count.count = 1;
        count.correct = 0;

        MyString myString = new MyString();
        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse q1 = getQuestion.nextQuestion(1);
        Model model = new ExtendedModelMap();
        controller.questionFormPOST(count, myString, q1.getAnswer().toString(), model);

        assertThat(count.correct, is(1));
    }

    @Test
    void testIncorrectAnswerIncrementsIncorrectCount() {
        Count count = new Count();
        count.count = 1;
        count.incorrect = 0;

        MyString myString = new MyString();
        GetQuestion getQuestion = new GetQuestion();
        QuestionTrueFalse q1 = getQuestion.nextQuestion(1);
        String wrong = Boolean.toString(!q1.getAnswer());
        Model model = new ExtendedModelMap();
        controller.questionFormPOST(count, myString, wrong, model);

        assertThat(count.incorrect, is(1));
    }

    @Test
    void testNumberOfCorrectAnswersFullQuiz() {
        int totalQuestions = 5;
        Count count = new Count();
        count.count = 1;
        MyString myString = new MyString();
        Model model = new ExtendedModelMap();
        GetQuestion getQuestion = new GetQuestion();

        for (int i = 1; i <= totalQuestions; i++) {
            QuestionTrueFalse q = getQuestion.nextQuestion(i);
            controller.questionFormPOST(count, myString, Boolean.toString(q.getAnswer()), model);
        }

        assertThat(count.correct, is(totalQuestions));
        assertThat(count.incorrect, is(0));
    }
}
