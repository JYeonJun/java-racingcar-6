package racingcar.service;

import java.util.ArrayList;
import java.util.List;
import racingcar.domain.Car;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import racingcar.util.RacingCarVO;

public class RacingCarServiceImpl implements RacingCarService {

    /**
     * 자동차 이름 입력 받아 생성하고 반환하는 함수 (예외처리 필요)
     *
     * @return 생성된 Car 리스트
     */
    @Override
    public List<Car> createCarList() {
        try {
            String userInput = Console.readLine();
            String[] carNames = userInput.split(",");
            validateCarNames(carNames);
            return createCars(carNames);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 시도할 회수 입력받아 반환하는 함수 (예외처리 필요)
     *
     * @return 사용자로부터 입력 받은 시도 횟수
     */
    @Override
    public int getAttemptCount() {
        try {
            return readAndParseAttemptCount();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * 무작위 값 생성해서 자동차 이동시키는 함수
     * @param carList : Car 객체 리스트
     */
    @Override
    public void moveCars(List<Car> carList) {

        for (Car car : carList) {
            int randomValue = generateRandomValue();

            if (isMoveForward(randomValue)) {
                car.moveForward();
            }
        }
    }

    /**
     * 자동차 이동 상황 출력하는 함수
     * @param carList : Car 객체 리스트
     */
    @Override
    public void printMovementCounts(List<Car> carList) {

    }

    @Override
    public void announceWinner(List<Car> carList) {

    }

    private void validateCarNames(String[] carNames) {
        for (String carName : carNames) {
            if (carName.length() > 5) {
                throw new IllegalArgumentException();
            }
        }
    }

    private List<Car> createCars(String[] carNames) {
        List<Car> carList = new ArrayList<>();
        for (String carName : carNames) {
            Car car = Car.createCar(carName);
            carList.add(car);
        }
        return carList;
    }

    private int readAndParseAttemptCount() {
        String input = Console.readLine();
        return Integer.parseInt(input);
    }

    private int generateRandomValue() {
        int randomMinValue = RacingCarVO.getRandomMinValue();
        int randomMaxValue = RacingCarVO.getRandomMaxValue();
        return Randoms.pickNumberInRange(randomMinValue, randomMaxValue);
    }

    private boolean isMoveForward(int randomValue) {
        return randomValue >= RacingCarVO.getMoveThreshold();
    }
}
