import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';

import { ISpecimen } from 'app/shared/model/specimen.model';
import { IPicture } from 'app/shared/model/picture.model';
import { PictureSearchService } from 'app/shared/search/picture-search.service';

// import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap'; 

@Component({
  selector: 'jhi-specimen-detail',
  templateUrl: './specimen-detail.component.html'
})
export class SpecimenDetailComponent implements OnInit {
  specimen: ISpecimen | null = null;
  pictures: IPicture[] = [];

  constructor(protected activatedRoute: ActivatedRoute, private pictureSearchService: PictureSearchService /* , config: NgbCarouselConfig*/ ) {
//      config.interval = 0;  
//      config.wrap = true;  
//      config.keyboard = false;  
//      config.pauseOnHover = false; 
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ specimen }) => this.onSpecimenReceived(specimen));
  }
  
  onSpecimenReceived(specimen: ISpecimen): void {
      this.specimen = specimen;
      if (specimen.id) {
          this.pictureSearchService.getThumbnailsForSpecimen(specimen?.id, 250, 0).subscribe(( res: HttpResponse<IPicture[]> ) => this.onPicturesReceived( res.body || [] ));
      }
  }
  
  onPicturesReceived(pictures: IPicture[]): void {
      this.pictures = pictures;
  }

  previousState(): void {
    window.history.back();
  }
}
