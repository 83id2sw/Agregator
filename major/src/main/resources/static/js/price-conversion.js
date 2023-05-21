window.onload = function () {
    let prices = document.getElementsByClassName('item-price');
    for (let i = 0; i < prices.length; i++) {
        prices[i].innerHTML = prices[i].textContent + ' ≈ ₽' + Math.round(prices[i].textContent.slice(1) * 79.91)
    }
}


