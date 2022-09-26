import exportFromJSON from "export-from-json";

function downloadXML (auction,id) {
    const data = {auction}
    const fileName = "auction_item_"+String(id)
    let fields = []
    const exportType = 'xml';
    exportFromJSON({data,fileName,fields,exportType})
}

export default downloadXML