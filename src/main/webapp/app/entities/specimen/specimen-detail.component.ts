import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { ISpecimen } from 'app/shared/model/specimen.model';
import { IPicture } from 'app/shared/model/picture.model';
import { PictureSearchService } from 'app/shared/search/picture-search.service';
import { PictureService } from 'app/entities/picture/picture.service';

// import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap'; 

@Component({
  selector: 'jhi-specimen-detail',
  templateUrl: './specimen-detail.component.html'
})
export class SpecimenDetailComponent implements OnInit {
  specimen: ISpecimen | null = null;
  pictures: IPicture[] = [];

  constructor(protected activatedRoute: ActivatedRoute, private pictureSearchService: PictureSearchService, protected pictureService: PictureService, 
          private router: Router /* , config: NgbCarouselConfig*/ ) {
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
  
  addPicture(): void {
      this.pictureService.setSpecimen(this.specimen || {});
      this.router.navigateByUrl( '/picture/new' );      
  }

  previousState(): void {
    window.history.back();
  }
}
