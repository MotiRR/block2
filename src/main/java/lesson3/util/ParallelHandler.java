package lesson3.util;

import lesson3.database.dao.BetDAO;
import lesson3.database.dto.User;

import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParallelHandler {

    private Service service = new Service();
    private List<User> users;

    public ParallelHandler() {
        users = service.getAllUsers();
    }

    public void start(int count, BigDecimal rate, LockModeType lockModeType) {
        long start = System.currentTimeMillis();
        BetDAO.countRollBack = 0L;
        createThreads(count, rate, lockModeType);
        System.out.format("Блокировка %s, время работы = %d мс, количество rollback = %d\n",
                lockModeType,
                System.currentTimeMillis() - start,
                BetDAO.countRollBack);
    }


    /**
     * Создание потоков под каждого пользователя.
     * @param  count - количество запросов к базе
     * @param rate - ставка
     * @param lockModeType - тип блокировки
     * */
    private void createThreads(int count, BigDecimal rate, LockModeType lockModeType) {
        List<Thread> threads = new ArrayList<>();
        for (User user : users) {
            threads.add(new Thread(() -> {
                Random random = new Random();
                for (int i = 0; i < count; i++) {
                    Long r = (long) random.ints(1, 5).findFirst().orElse(1);
                    service.updateBet(r, user, rate, lockModeType);
                }
            }));
        }
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
