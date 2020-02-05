import * as React from 'react';



class GiphyImage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            giphyUrl: '',
            isLoading: false
        };
    }

    componentDidUpdate(prevProps) {
        const giphyApi = '//api.giphy.com/v1/gifs/search?api_key=dc6zaTOxFJmzC&limit=1&q=';
        if (prevProps.name !== this.props.name) {

        fetch(giphyApi + this.props.name)
            .then(response => response.json())
            .then(response => {
                if (response.data.length > 0) {
                    this.setState({giphyUrl: response.data[0].images.original.url});
                } else {
                    // dancing cat for no images found
                    this.setState({giphyUrl: '//media.giphy.com/media/YaOxRsmrv9IeA/giphy.gif'});
                }
                this.setState({isLoading: false});
            });
        }
    }

    render() {
        console.log(this.props.name)

        const {giphyUrl, isLoading} = this.state;
        console.log(giphyUrl)
        if (isLoading) {
            return <p>Loading image...</p>;
        }

        return (
            <img src={giphyUrl} alt={this.props.name} width="500"/>
        );
    }
}

export default GiphyImage;