package ua.vin.five.thymeleafMongoDB.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.stereotype.Component;
import ua.vin.five.thymeleafMongoDB.models.Book;
import ua.vin.five.thymeleafMongoDB.services.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

@Component
public class BookModelListener extends AbstractMongoEventListener<Book> {

    private SequenceGeneratorService sequenceGenerator;

    @Autowired
    public BookModelListener(SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);

        if(event.getSource().getId() < 1){
            event.getSource().setId(sequenceGenerator.generateSequence(Book.SEQUENCE_NAME));
        }

    }

}
