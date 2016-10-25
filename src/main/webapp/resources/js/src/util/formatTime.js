const second = 1000;
const minute = 60 * second;
const hour = 60 * minute;

function pad(value) {
    if (value.toString().length === 1) {
        return `0${value}`;
    } else {
        return `${value}`;
    }
}

export function formatTime(ms) {
    let hours = 0;
    let minutes = 0;
    let seconds = 0;
    let remainder = ms;

    hours = Math.floor(remainder / hour);

    remainder -= hours * hour;

    if (remainder > 0) {
        minutes = Math.floor(remainder / minute);

        remainder -= minutes * minute;

        if (remainder > 0) {
            seconds = Math.floor(remainder / second);
        }
    }

    return `${pad(hours)}:${pad(minutes)}:${pad(seconds)}`
}