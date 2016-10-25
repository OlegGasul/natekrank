import {formatTime} from '../util/formatTime';
import countDownTemplate from '../templates/countDown.html';

function CountDownController($timeout) {
    const startedAt = this.startedAt;
    const endingAt = startedAt + this.testDuration;

    this.elapsing = false;

    const update = () => {
        let left = endingAt - Date.now();

        if (left < 0) {
            left = 0;
        }

        this.timeLeft = formatTime(left);

        if (left <= this.testDuration / 10) {
            this.elapsing = true;
        }

        if (left === 0) {
            console.log('calling onExpire');
            this.onExpire();
        } else {
            $timeout(update, 100);
        }
    };

    $timeout(update, 0);
}

CountDownController.$inject = ['$timeout'];

export const CountDown = {
    template: countDownTemplate,
    bindings: {
        startedAt: '<',
        // pageLoadedAt: '<',
        testDuration: '<',
        onExpire: '&',
    },

    controller: CountDownController,
};