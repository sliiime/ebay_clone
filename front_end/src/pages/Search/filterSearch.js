const filterSearch = (search) => {
    let filters = []

    if (search.text) {
        let filter = {
            field: "description",
            operator: "LIKE",
            value: search.text
        }
        filters.push(filter)
    }
    if (search.text) {
        let filter = {
            field: "name",
            operator: "LIKE",
            value: search.text
        }
        filters.push(filter)
    }
    if (search.country) {
        let filter = {
            field: "country",
            operator: "EQUALS",
            value: search.country
        }
        filters.push(filter)
    }
    if (search.minPrice) {
        let filter = {
            field: "buyPrice",
            operator: "GREATER_THAN",
            value: search.minPrice
        }
        filters.push(filter)
    }
    if (search.maxPrice) {
        let filter = {
            field: "buyPrice",
            operator: "LESS_THAN",
            value: search.maxPrice
        }
        filters.push(filter)
    }
    if (search.categories.length>0) {
        let filter = {
            field: "category",
            operator: "IN",
            values: search.categories
        }
        filters.push(filter)
    }
    console.log(filters)
    return filters
}

export default filterSearch;