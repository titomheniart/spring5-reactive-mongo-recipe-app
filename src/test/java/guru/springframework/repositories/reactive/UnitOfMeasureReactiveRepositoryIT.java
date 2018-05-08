package guru.springframework.repositories.reactive;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureReactiveRepositoryIT {

    private final static String UOM_DESCRIPTION = "uom";

    @Autowired
    UnitOfMeasureReactiveRepository unitOfMeasureReactiveRepository;

    @Before
    public void setUp() throws Exception {
        unitOfMeasureReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(UOM_DESCRIPTION);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        assertEquals(Long.valueOf(1L), unitOfMeasureReactiveRepository.count().block());
    }

    @Test
    public void testFindByDescription() {
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        unitOfMeasure.setDescription(UOM_DESCRIPTION);

        unitOfMeasureReactiveRepository.save(unitOfMeasure).block();

        assertNotNull(unitOfMeasureReactiveRepository.findByDescription(UOM_DESCRIPTION).block().getId());
    }
}
