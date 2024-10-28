import TitleCard from "../../../components/Cards/TitleCard"

const userSourceData = [
    {source : "Facebook Ads", count : "26,345", conversionPercent : 10.2},
    {source : "Google Ads", count : "21,341", conversionPercent : 11.7},
    {source : "Instagram Ads", count : "34,379", conversionPercent : 12.4},
    {source : "Affiliates", count : "12,359", conversionPercent : 20.9},
    {source : "Organic", count : "10,345", conversionPercent : 10.3},
]

function UserChannels({hashmap}){
    const labels = Object.keys(hashmap)
    return(
        <TitleCard title={"Products"}>
             {/** Table Data */}
             <div className="overflow-x-auto max-h-96 overflow-y-auto">
                <table className="table w-full">
                    <thead>
                    <tr>
                        <th>Product</th>
                        <th className="normal-case">Quantity</th>
                    </tr>
                    </thead>
                    <tbody>
                        {
                            labels.map((u, k) => {
                                return(
                                    <tr key={k}>
                                        <th>{u}</th>
                                        <th>{hashmap[u]}</th>
                                    </tr>
                                )
                            })
                        }
                    </tbody>
                </table>
            </div>
        </TitleCard>
    )
}

export default UserChannels