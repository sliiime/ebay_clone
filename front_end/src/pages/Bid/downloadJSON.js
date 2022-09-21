function downloadJSON (auction,id) {
    //console.log(auction)
    const data = JSON.stringify(auction)
    const blob = new Blob([data], { type: 'text/json' })

    const file = document.createElement('a')
    file.download = "auction_item_"+String(id)+".json"
    file.href = window.URL.createObjectURL(blob)
    const clickEvt = new MouseEvent('click', {
        view: window,
        bubbles: true,
        cancelable: true,
    })
    file.dispatchEvent(clickEvt)
    file.remove()

    // const fileName = "auction_item_"+String(id)
    // const json = JSON.stringify(auction, null, 2);
    // const blob = new Blob([{auction}], { type: "application/json" });
    // const href = URL.createObjectURL(blob);
    //
    // // create "a" HTLM element with href to file
    // const link = document.createElement("a");
    // link.href = href;
    // link.download = fileName + ".json";
    // document.body.appendChild(link);
    // link.click();
    //
    // // clean up "a" element & remove ObjectURL
    // document.body.removeChild(link);
    // URL.revokeObjectURL(href);
}

export default downloadJSON