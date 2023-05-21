window.onload = function () {
    let prices = document.getElementsByClassName('item-price');
    console.log(prices)
    for (let i = 0; i < prices.length; i++) {
        console.log(prices[i].textContent)
        prices[i].innerHTML = prices[i].textContent + ' ≈ ₽' + Math.round(prices[i].textContent.slice(1) * 79.98)
    }
}


